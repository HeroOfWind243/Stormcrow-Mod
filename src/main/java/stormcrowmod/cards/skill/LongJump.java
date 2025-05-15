package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.AfterburnerAction;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;

public class LongJump extends BaseCard {
    public static final String ID = makeID(LongJump.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    private static final int MAGIC_2 = 3;
    private static final int UPG_MAGIC_2 = 1;

    public LongJump() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        setCustomVar("magic2", MAGIC_2, UPG_MAGIC_2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
        addToBot(new ImpulseAction(p, customVar("magic2")));
    }

    @Override
    public AbstractCard makeCopy() {
        return new LongJump();
    }
}
