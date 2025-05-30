package stormcrowmod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;

import static stormcrowmod.StormcrowMod.makeID;

public class LoseStrNextTurnPower extends BasePower implements BetterOnApplyPowerPower {
    public static final String POWER_ID = makeID("LoseStrNextTurn");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public LoseStrNextTurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        if (this.amount > 0) {
            addToBot(new ApplyPowerAction(owner, owner, new LoseStrengthPower(owner, this.amount)));
        }
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (target == this.owner && !this.owner.hasPower("Artifact") && power.ID.equals("Flex")) {
            stackPower(power.amount);
            updateDescription();
            return false;
        }

        return true;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
