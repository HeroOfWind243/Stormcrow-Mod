package stormcrowmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class MegaThruster extends BaseCard {
    public static final String ID = makeID(MegaThruster.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2 //Can use -1 for X, or -2 for unplayable
    );

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 2;

    public MegaThruster() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.cardsToPreview = new Thruster();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                count++;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                count++;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                count++;
            }
        }

        addToBot(new VFXAction(new WhirlwindEffect()));

        for (int i = 0; i < count; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                count++;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                count++;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                count++;
            }
        }

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                count++;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                count++;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                count++;
            }
        }

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new MegaThruster();
    }
}
