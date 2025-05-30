package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.powers.MomentumPower;
import stormcrowmod.powers.PulseOffPower;

import static stormcrowmod.StormcrowMod.makeID;

public class PulseAction extends AbstractGameAction {
    private final AbstractGameAction pulseAction;
    private final AbstractGameAction pulseAction2;
    private final AbstractGameAction pulseAction3;

    public PulseAction() {
        this.pulseAction = null;
        this.pulseAction2 = null;
        this.pulseAction3 = null;
        this.actionType = ActionType.SPECIAL;
    }

    public PulseAction(AbstractGameAction a) {
        this.pulseAction = a;
        this.pulseAction2 = null;
        this.pulseAction3 = null;
        this.actionType = ActionType.SPECIAL;
    }

    public PulseAction(AbstractGameAction a, AbstractGameAction b) {
        this.pulseAction = a;
        this.pulseAction2 = b;
        this.pulseAction3 = null;
        this.actionType = ActionType.SPECIAL;
    }

    public PulseAction(AbstractGameAction a, AbstractGameAction b, AbstractGameAction c) {
        this.pulseAction = a;
        this.pulseAction2 = b;
        this.pulseAction3 = c;
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if (!p.hasPower(makeID("PulseOff"))) {
            if (p.hasPower(makeID("HopManeuvers"))) {
                addToTop(new ApplyPowerAction(p, p, new MomentumPower(p, p.getPower(makeID("HopManeuvers")).amount)));
            }

            addToTop(new ApplyPowerAction(p, p, new PulseOffPower(p)));
            if (pulseAction3 != null) {
                addToTop(pulseAction3);
            }
            if (pulseAction2 != null) {
                addToTop(pulseAction2);
            }
            if (pulseAction != null) {
                addToTop(pulseAction);
            }

        } else {
            addToTop(new RemoveSpecificPowerAction(p, p, makeID("PulseOff")));
        }
        this.isDone = true;
    }

}
