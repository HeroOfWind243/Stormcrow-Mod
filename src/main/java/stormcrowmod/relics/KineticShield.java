package stormcrowmod.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import stormcrowmod.character.PilotCharacter;

import static stormcrowmod.StormcrowMod.makeID;

public class KineticShield extends BaseRelic {
    private static final String NAME = "KineticShield";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    private final int BLOCK = 1;

    public KineticShield() {
        super(ID, NAME, PilotCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        flash();
        addToBot(new GainBlockAction(AbstractDungeon.player, this.BLOCK));
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(ParticleShield.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(ParticleShield.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(ParticleShield.ID);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK + DESCRIPTIONS[1];
    }
}
