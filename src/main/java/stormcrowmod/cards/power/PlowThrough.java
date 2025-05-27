package stormcrowmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.ImpulsivePower;
import stormcrowmod.powers.PlowThroughPower;
import stormcrowmod.util.CardStats;

public class PlowThrough extends BaseCard {
    public static final String ID = makeID(PlowThrough.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2 //Can use -1 for X, or -2 for unplayable
    );

    public PlowThrough() {
        super(ID, info);
        setCostUpgrade(1);
        cardsToPreview = new Impact();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PlowThroughPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new PlowThrough();
    }
}
