package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import stormcrowmod.powers.PulseOffPower;
import stormcrowmod.util.PilotTags;

import static stormcrowmod.StormcrowMod.makeID;

public class AfterburnerAction extends AbstractGameAction {
    private final int amountToDraw;
    private final int bonusToDraw;
    private final AbstractCreature t;

    public AfterburnerAction(AbstractCreature target, int amountToDraw, int bonusToDraw) {
        this.amountToDraw = amountToDraw;
        this.bonusToDraw = bonusToDraw;
        this.t = target;
        this.actionType = ActionType.DRAW;
    }


    public void update() {
        addToBot(new DrawCardAction(t, amountToDraw));
        addToBot(new AfterburnerFollowupAction(t, bonusToDraw));
        this.isDone = true;

    }

}
