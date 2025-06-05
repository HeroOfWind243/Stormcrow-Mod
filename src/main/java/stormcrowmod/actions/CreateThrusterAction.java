package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.cards.created.ThrusterV2;
import stormcrowmod.powers.PulseOffPower;

import static stormcrowmod.StormcrowMod.makeID;

public class CreateThrusterAction extends AbstractGameAction {
    private final int amount;
    private final boolean upgraded;

    public CreateThrusterAction(int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.amount = amount;
        upgraded = false;
    }

    public CreateThrusterAction(int amount, boolean upgraded) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.amount = amount;
        this.upgraded = upgraded;
    }

    public void update() {
        AbstractCard thruster = new ThrusterV2();
        if (this.upgraded) {
            thruster.upgrade();
        }

        addToTop(new MakeTempCardInDrawPileAction(thruster, this.amount, true, true, false));
        this.isDone = true;
    }

}
