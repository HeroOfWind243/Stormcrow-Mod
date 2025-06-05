package stormcrowmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static stormcrowmod.StormcrowMod.makeID;

public class MomentumPower extends BasePower {
    public static final String POWER_ID = makeID("Momentum");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public MomentumPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        stackPower(2);
        this.updateDescription();
    }

//    @Override
//    public void wasHPLost(DamageInfo info, int damageAmount) {
//        if (damageAmount > 0) {
//            reducePower(Math.min(this.amount, damageAmount));
//        }
//        if (this.amount == 0) {
//            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
//        }
//        this.updateDescription();
//    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        int half_m_rounded_up = (int) Math.ceil(this.amount / 2.0);
        addToBot(new ReducePowerAction(owner, owner, POWER_ID, half_m_rounded_up));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
