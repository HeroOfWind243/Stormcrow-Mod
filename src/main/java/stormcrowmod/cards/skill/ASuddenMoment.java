package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;

public class ASuddenMoment extends BaseCard {
    public static final String ID = makeID(ASuddenMoment.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 5;

    public ASuddenMoment() {
        super(ID, info);

        setSelfRetain(true);
        setExhaust(true);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ImpulseAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ASuddenMoment();
    }
}
