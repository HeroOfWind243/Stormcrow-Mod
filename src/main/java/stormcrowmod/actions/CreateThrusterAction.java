package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.powers.PulseOffPower;

import static stormcrowmod.StormcrowMod.makeID;

public class CreateThrusterAction extends AbstractGameAction {
    private final int amount;

    public CreateThrusterAction(int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.amount = amount;
    }

    public void update() {
        addToTop(new MakeTempCardInDrawPileAction(new Thruster(), this.amount, true, true, false));
        this.isDone = true;
    }

}
