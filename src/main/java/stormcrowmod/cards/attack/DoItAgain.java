package stormcrowmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.CreateThrusterAction;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class DoItAgain extends BaseCard {
    public static final String ID = makeID(DoItAgain.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 3;

    public DoItAgain() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(PilotTags.PULSE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new PulseAction(new DiscardPileToTopOfDeckAction(p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DoItAgain();
    }
}
