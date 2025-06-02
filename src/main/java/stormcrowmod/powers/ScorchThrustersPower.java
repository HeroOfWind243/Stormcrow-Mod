package stormcrowmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.util.PilotTags;

import static stormcrowmod.StormcrowMod.makeID;

public class ScorchThrustersPower extends BasePower {
    public static final String POWER_ID = makeID("ScorchThrusters");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ScorchThrustersPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(PilotTags.THRUSTER)) {
            flash();
            int dmg = card.block * amount;
            addToBot(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(dmg,false), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE, true));
        }
    }

    @Override
    public void updateDescription() {
        if (amount > 1) {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[2] + amount + " times" + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        }

    }
}
