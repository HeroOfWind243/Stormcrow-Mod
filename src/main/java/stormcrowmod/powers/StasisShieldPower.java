package stormcrowmod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.powers.NoBlockPower;

import static stormcrowmod.StormcrowMod.makeID;

public class StasisShieldPower extends BasePower implements NonStackablePower {
    public static final String POWER_ID = makeID("StasisShield");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    private int stasisBlock;

    public StasisShieldPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
        stasisBlock = 0;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        stasisBlock = owner.currentBlock;

    }

    @Override
    public void atStartOfTurn() {
        addToTop(new ApplyPowerAction(owner, owner, new NoBlockPower(owner, 1, false)));
        addToTop(new GainBlockAction(owner, stasisBlock));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
