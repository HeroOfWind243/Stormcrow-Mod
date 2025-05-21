package stormcrowmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.HopManeuversPower;
import stormcrowmod.powers.ReactionForcePower;
import stormcrowmod.util.CardStats;

public class ReactionForce extends BaseCard {
    public static final String ID = makeID(ReactionForce.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2 //Can use -1 for X, or -2 for unplayable
    );

    public ReactionForce() {
        super(ID, info);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ReactionForcePower(p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ReactionForce();
    }
}
