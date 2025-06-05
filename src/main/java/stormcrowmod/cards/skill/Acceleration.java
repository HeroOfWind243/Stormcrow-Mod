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


    public Acceleration() {
        super(ID, info);

        this.cardsToPreview = new Thruster();
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CreateThrusterAction(this.magicNumber, this.upgraded));
    }

    @Override
    public void applyPowers() {
        if (!this.cardsToPreview.upgraded && this.upgraded) {
            this.cardsToPreview.upgrade();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Acceleration();
    }
}
