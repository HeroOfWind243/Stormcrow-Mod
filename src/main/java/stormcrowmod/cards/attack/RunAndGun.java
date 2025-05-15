package stormcrowmod.cards.attack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import stormcrowmod.actions.CreateThrusterAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;

import java.util.function.Consumer;

public class RunAndGun extends BaseCard {
    public static final String ID = makeID(RunAndGun.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 9;

    public RunAndGun() {
        super(ID, info);

        this.cardsToPreview = new Thruster();
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Consumer<Integer> damageDealt = b -> {
            addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, b)));
        };
        
        addToBot(new DamageCallbackAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL, damageDealt));

    }

    @Override
    public AbstractCard makeCopy() {
        return new RunAndGun();
    }
}
