package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.powers.LoseDexNextTurnPower;
import stormcrowmod.powers.LoseStrNextTurnPower;

public class DelayTempStrDexAction extends AbstractGameAction {
    private final AbstractCreature t;

    public DelayTempStrDexAction(AbstractCreature target) {
        this.t = target;
        this.actionType = ActionType.POWER;
    }


    public void update() {
        int tempStr = 0;
        int tempDex = 0;

        if (t.hasPower("Flex")) {
            tempStr = t.getPower("Flex").amount;
        }

        if (t.hasPower("DexLoss")) {
            tempDex = t.getPower("DexLoss").amount;
        }

        addToBot(new RemoveSpecificPowerAction(t, t, "Flex"));
        addToBot(new ApplyPowerAction(t, t, new LoseStrNextTurnPower(t, tempStr), tempStr, true));

        addToBot(new RemoveSpecificPowerAction(t, t, "DexLoss"));
        addToBot(new ApplyPowerAction(t, t, new LoseDexNextTurnPower(t, tempDex), tempDex, true));


        this.isDone = true;

    }

}
