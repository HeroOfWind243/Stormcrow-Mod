package stormcrowmod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import stormcrowmod.StormcrowMod;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.character.PilotCharacter;

import static stormcrowmod.StormcrowMod.makeID;

public class EnginePrimer extends BasePotion {

    public static final String ID = makeID(EnginePrimer.class.getSimpleName());

    private static final Color LIQUID_COLOR = CardHelper.getColor(24, 106, 237);
    private static final Color HYBRID_COLOR = null;
    private static final Color SPOTS_COLOR = null;

    private static final int POTENCY = 10;

    public EnginePrimer() {
        super(ID, POTENCY, PotionRarity.COMMON, PotionSize.M, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        playerClass = PilotCharacter.Meta.STORMCROW_PILOT;
    }

    @Override
    public String getDescription() {
        return DESCRIPTIONS[0] + this.potency;
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot(new ImpulseAction(AbstractDungeon.player, this.potency));
        }
    }

    @Override
    public void addAdditionalTips() {
        //Adding a tooltip for Impulse
        this.tips.add(new PowerTip(StormcrowMod.keywords.get("impulse").PROPER_NAME, StormcrowMod.keywords.get("impulse").DESCRIPTION));
    }
}
