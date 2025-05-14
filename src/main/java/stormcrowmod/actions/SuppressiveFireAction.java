package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class SuppressiveFireAction extends AbstractGameAction {
    private final DamageInfo info;
    private final int weakAmount;

    public SuppressiveFireAction(DamageInfo info, AbstractGameAction.AttackEffect effect, int weakAmount) {
        this.info = info;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
        this.weakAmount = weakAmount;
    }

    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
            this.addToTop(new ApplyPowerAction(this.target, this.target, new WeakPower(this.target, this.weakAmount, false)));
            this.addToTop(new DamageAction(this.target, this.info, this.attackEffect));
        }

        this.target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (this.target != null) {
            this.addToTop(new ApplyPowerAction(this.target, this.target, new WeakPower(this.target, this.weakAmount, false)));
            this.addToTop(new DamageAction(this.target, this.info, this.attackEffect));
        }

        this.isDone = true;
    }
}
