package stormcrowmod.cards.skill;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import stormcrowmod.actions.ReThrustAction;
import stormcrowmod.actions.WristHolsterAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ReThrust extends BaseCard {
    public static final String ID = makeID(ReThrust.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            2 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 1;

    public ReThrust() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        this.cardsToPreview = new Thruster();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new SFXAction("ATTACK_FLAME_BARRIER", 0.5f, true));
        addToBot(new VFXAction(new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
        addToBot(new ReThrustAction(p));
        addToBot(new DrawCardAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ReThrust();
    }
}
