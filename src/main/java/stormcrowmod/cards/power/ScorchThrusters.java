package stormcrowmod.cards.power;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.ScorchThrustersPower;
import stormcrowmod.powers.UnstoppablePower;
import stormcrowmod.util.CardStats;

public class ScorchThrusters extends BaseCard {
    public static final String ID = makeID(ScorchThrusters.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2 //Can use -1 for X, or -2 for unplayable
    );

    private final static int MAGIC = 4;

    public ScorchThrusters() {
        super(ID, info);
        cardsToPreview = new Thruster();
        setMagic(MAGIC);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_FLAME_BARRIER"));
        addToBot(new ApplyPowerAction(p, p, new ScorchThrustersPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ScorchThrusters();
    }
}
