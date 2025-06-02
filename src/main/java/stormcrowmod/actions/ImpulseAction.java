package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.powers.MomentumPower;
import stormcrowmod.util.PilotTags;

public class ImpulseAction extends AbstractGameAction {
    private final int momentumToGrant;
    private final AbstractCreature target;

    public ImpulseAction(AbstractCreature t, int momentumToGrant) {
        this.momentumToGrant = momentumToGrant;
        this.target = t;
        this.actionType = ActionType.CARD_MANIPULATION;

    }

    public void update() {

        boolean hasAnImpact = false;

        if (target instanceof AbstractPlayer) {
            for (AbstractCard c : ((AbstractPlayer) target).hand.group) {
                if (c.hasTag(PilotTags.IMPACT)) {
                    hasAnImpact = true;
                }
            }
            addToTop(new ApplyPowerAction(target, target, new MomentumPower(target, momentumToGrant)));
            if (!hasAnImpact) {
                Impact impact = new Impact();
                addToTop(new MakeTempCardInHandAction(impact, 1));
                addToTop(new VFXAction(target, new FlameBarrierEffect(target.hb.cX, target.hb.cY), 0.1F));
            }
            // TODO: Add a dialog popup explaining why you can't stack Impacts

        }
        this.isDone = true;
    }

}
