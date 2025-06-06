package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.CreateThrusterAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;

public class Prechargers extends BaseCard {
    public static final String ID = makeID(Prechargers.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            0 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public Prechargers() {
        super(ID, info);
        setInnate(true);
        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
        this.cardsToPreview = new Thruster();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CreateThrusterAction(this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Prechargers();
    }
}
