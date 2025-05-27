package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.CreateThrusterAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.FullStopPower;
import stormcrowmod.powers.MomentumPower;
import stormcrowmod.util.CardStats;

public class FullStop extends BaseCard {
    public static final String ID = makeID(FullStop.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public FullStop() {
        super(ID, info);
        setCostUpgrade(0);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (p.hasPower(makeID("Momentum"))) {
            addToBot(new ApplyPowerAction(p, p, new MomentumPower(p, p.getPower(makeID("Momentum")).amount)));
        }
        addToBot(new ApplyPowerAction(p, p, new FullStopPower(p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FullStop();
    }
}
