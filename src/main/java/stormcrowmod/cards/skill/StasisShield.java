package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.StasisShieldPower;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class StasisShield extends BaseCard {
    public static final String ID = makeID(StasisShield.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2 //Can use -1 for X, or -2 for unplayable
    );


    private static final int BLOCK = 11;
    private static final int UPG_BLOCK = 3;

    public StasisShield() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new ApplyPowerAction(p, p, new StasisShieldPower(p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new StasisShield();
    }
}
