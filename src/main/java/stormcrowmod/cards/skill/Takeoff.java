package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;

public class Takeoff extends BaseCard {
    public static final String ID = makeID(Takeoff.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 0;

    private static final int UPG_COST = 0;

    private static final int MAGIC = 1;

    public Takeoff() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC);
        setCostUpgrade(UPG_COST);
        this.cardsToPreview = new Impact();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new ImpulseAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Takeoff();
    }
}
