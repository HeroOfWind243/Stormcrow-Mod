package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class Inertia extends BaseCard {
    public static final String ID = makeID(Inertia.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int BLOCK = 0;

    public Inertia() {
        super(ID, info);
        setExhaust(true, false);
        setBlock(BLOCK);
        setMagic(0);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        initializeDescription();
        int half_m_rounded_up = (int)Math.ceil(this.currentMomentum(AbstractDungeon.player) / 2.0);
        addToBot(new ReducePowerAction(p,p, makeID("Momentum"), half_m_rounded_up));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Inertia();
    }

    @Override
    public void applyPowers() {
        this.baseBlock = BLOCK;
        this.baseBlock += currentMomentum(AbstractDungeon.player);
        super.applyPowers();
        initializeDescription();
    }

    private int currentMomentum(AbstractPlayer p) { //Check that avoids returning null if Momentum is not present
        if (p.hasPower(makeID("Momentum"))) {
            return p.getPower(makeID("Momentum")).amount;
        }
        return 0;
    }
}
