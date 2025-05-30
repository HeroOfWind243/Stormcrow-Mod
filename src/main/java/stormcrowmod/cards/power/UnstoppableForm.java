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
import stormcrowmod.powers.PulseThrustersPower;
import stormcrowmod.powers.UnstoppablePower;
import stormcrowmod.util.CardStats;

public class UnstoppableForm extends BaseCard {
    public static final String ID = makeID(UnstoppableForm.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 8;

    public UnstoppableForm() {
        super(ID, info);
        setEthereal(true, false);
        setMagic(MAGIC);
        cardsToPreview = new Impact();
        this.tags.add(BaseModCardTags.FORM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("RAGE"));
        addToBot(new ApplyPowerAction(p, p, new UnstoppablePower(p)));
        addToBot(new ImpulseAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new UnstoppableForm();
    }
}
