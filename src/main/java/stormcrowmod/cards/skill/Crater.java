package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.CraterPower;
import stormcrowmod.powers.MomentumOnPlayPower;
import stormcrowmod.powers.NextTurnImpulsePower;
import stormcrowmod.util.CardStats;

public class Crater extends BaseCard {
    public static final String ID = makeID(Crater.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    public Crater() {
        super(ID, info);

        setCostUpgrade(0);

        this.cardsToPreview = new Impact();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CraterPower(p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Crater();
    }
}
