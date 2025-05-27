package stormcrowmod.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.actions.CreateThrusterAction;
import stormcrowmod.cards.created.Thruster;

import static stormcrowmod.StormcrowMod.makeID;

public class ChargeThrustersPower extends BasePower {
    public static final String POWER_ID = makeID("ChargeThrusters");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public ChargeThrustersPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToTop(new MakeTempCardInDrawPileAction(new Thruster(), 1, false, true));
        addToTop(new GainEnergyAction(1));
        reducePower(1);
        if (amount <= 0) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }

    }
}
