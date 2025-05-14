package stormcrowmod.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import stormcrowmod.character.PilotCharacter;

import static stormcrowmod.StormcrowMod.makeID;

public class ParticleShield extends BaseRelic {
    private static final String NAME = "ParticleShield";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.CLINK;

    private final int BLOCK = 8;

    public ParticleShield() {
        super(ID, NAME, PilotCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new GainBlockAction(AbstractDungeon.player, this.BLOCK));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK + DESCRIPTIONS[1];
    }
}
