package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import stormcrowmod.actions.CreateThrusterAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Empower;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.RechargeShieldsPower;
import stormcrowmod.util.CardStats;

public class RechargeShields extends BaseCard {
    public static final String ID = makeID(RechargeShields.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    public RechargeShields() {
        super(ID, info);
        setExhaust(true);

        this.cardsToPreview = new Empower();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new RechargeShieldsPower(p, this.upgraded)));
    }

    @Override
    public void applyPowers() {
        if (!this.cardsToPreview.upgraded && this.upgraded) {
            this.cardsToPreview.upgrade();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new RechargeShields();
    }
}
