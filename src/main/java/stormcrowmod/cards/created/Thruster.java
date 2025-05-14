package stormcrowmod.cards.created;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class Thruster extends BaseCard {
    public static final String ID = makeID(Thruster.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0 //Can use -1 for X, or -2 for unplayable
    );

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;

    public Thruster() {
        super(ID, info);

        // TODO: Add checks for More Power and Pulse Thrusters here
        setBlock(BLOCK, UPG_BLOCK);

        setExhaust(true);

        //setCustomVar("totalBlock", BLOCK, UPG_BLOCK);

        tags.add(PilotTags.THRUSTER);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, 1));
    }

    @Override
    public void triggerWhenDrawn() {
        this.applyPowers();
        addToTop(new GainBlockAction(AbstractDungeon.player, this.block, true));
    }

    @Override
    public void applyPowers() {
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(makeID("MorePower"))) {
            int realBaseBlock = this.baseBlock;
            this.baseBlock = this.baseBlock + (AbstractDungeon.player.getPower(makeID("MorePower")).amount);
            super.applyPowers();
            this.baseBlock = realBaseBlock;
            this.isBlockModified = (this.block != this.baseBlock);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Thruster();
    }
}
