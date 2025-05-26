package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import stormcrowmod.actions.LoopAroundFollowupAction;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class LoopAround extends BaseCard {
    public static final String ID = makeID(LoopAround.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC_2 = 2;

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 1;

    public LoopAround() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("magic2", MAGIC_2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new DrawCardAction(p, this.magicNumber));
        addToBot(new LoopAroundFollowupAction(p, customVar("magic2")));
    }

    @Override
    public AbstractCard makeCopy() {
        return new LoopAround();
    }
}
