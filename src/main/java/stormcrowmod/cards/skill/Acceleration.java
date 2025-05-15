package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.CreateThrusterAction;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;

public class Acceleration extends BaseCard {
    public static final String ID = makeID(Acceleration.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    private static final int BLOCK = 5;


    public Acceleration() {
        super(ID, info);

        this.cardsToPreview = new Thruster();
        setMagic(MAGIC, UPG_MAGIC);
        setBlock(BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new CreateThrusterAction(this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Acceleration();
    }
}
