package stormcrowmod.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import stormcrowmod.cards.created.Invert;
import stormcrowmod.character.PilotCharacter;

import static stormcrowmod.StormcrowMod.makeID;

public class NotchFilter extends BaseRelic {
    private static final String NAME = "NotchFilter";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public NotchFilter() {
        super(ID, NAME, PilotCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart() {
        AbstractCard invert = new Invert();
        invert.upgrade();

        addToBot(new MakeTempCardInHandAction(invert));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
