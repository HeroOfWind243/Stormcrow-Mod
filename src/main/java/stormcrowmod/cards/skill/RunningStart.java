package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.CreateThrusterAction;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.RunningStartPower;
import stormcrowmod.util.CardStats;

public class RunningStart extends BaseCard {
    public static final String ID = makeID(RunningStart.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = 4;

    public RunningStart() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        this.cardsToPreview = new Impact();
        setInnate(true);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new ImpulseAction(p, this.magicNumber));
//        addToBot(new ApplyPowerAction(p, p, new RunningStartPower(p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RunningStart();
    }
}
