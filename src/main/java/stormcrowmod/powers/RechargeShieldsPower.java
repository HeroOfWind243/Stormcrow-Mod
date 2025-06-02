package stormcrowmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import stormcrowmod.cards.created.Empower;
import stormcrowmod.cards.created.Thruster;

import static stormcrowmod.StormcrowMod.makeID;

public class RechargeShieldsPower extends BasePower {
    public static final String POWER_ID = makeID("RechargeShields");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    private final boolean upgrade;

    public RechargeShieldsPower(AbstractCreature owner, boolean upgrade) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
        this.upgrade = upgrade;
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        int blockToRemove = (int) (owner.currentBlock / 2.0f);
        if (blockToRemove > 0) {
            addToTop(new DamageAction(owner, new DamageInfo(owner, blockToRemove, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SMASH));
        }
    }

    @Override
    public void atStartOfTurn() {
        flash();

        AbstractCard c = new Empower();
        if (this.upgrade) {
            c.upgrade();
        }

        addToBot(new MakeTempCardInHandAction(c));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (this.upgrade) {
            this.description += DESCRIPTIONS[2];
        } else {
            this.description += DESCRIPTIONS[1];
        }
    }
}
