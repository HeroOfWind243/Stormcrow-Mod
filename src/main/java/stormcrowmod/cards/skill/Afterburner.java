package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.AfterburnerAction;
import stormcrowmod.actions.CreateThrusterAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;

public class Afterburner extends BaseCard {
    public static final String ID = makeID(Afterburner.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Afterburner() {
        super(ID, info);
        this.cardsToPreview = new Thruster();

        setCustomVar("baseDraw", 2);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AfterburnerAction(p, customVar("baseDraw"), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Afterburner();
    }
}
