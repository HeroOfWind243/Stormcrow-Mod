package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import stormcrowmod.powers.ChargeThrustersPower;

public class ShattercrashAction extends AbstractGameAction {
    private boolean freeToPlayOnce = false;

    private boolean upgraded;

    private AbstractPlayer p;
    private AbstractMonster mo;

    private int energyOnUse;

    private AbstractCard impact;

    public ShattercrashAction(AbstractPlayer p, AbstractMonster mo, boolean freeToPlayOnce, int energyOnUse, boolean upgraded, AbstractCard impact) {
        this.p = p;
        this.mo = mo;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.upgraded = upgraded;
        this.impact = impact;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if (this.upgraded) {
            effect++;
        }

        int dmg = 0;

        if (mo != null) {
            impact.calculateCardDamage(mo);
            dmg = impact.damage;
        }
        addToBot(new ExhaustSpecificCardAction(impact, p.hand, true));

        if (effect > 0 && dmg > 0) {
            for (int j = 0; j < effect; j++) {
                addToBot(new VFXAction(new VerticalImpactEffect(mo.hb.cX + mo.hb.width / 4.0F, mo.hb.cY - mo.hb.height / 4.0F)));
                addToBot(new DamageAction(this.mo, new DamageInfo(this.p, dmg, DamageInfo.DamageType.NORMAL), AttackEffect.NONE));
            }

            if (!this.freeToPlayOnce)
                this.p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone = true;
    }
}
