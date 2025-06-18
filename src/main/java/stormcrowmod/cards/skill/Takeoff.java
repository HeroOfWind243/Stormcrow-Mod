package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.AttackFormation;
import stormcrowmod.cards.created.GuardFormation;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;

import java.util.ArrayList;

public class Takeoff extends BaseCard {
    public static final String ID = makeID(Takeoff.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 3;

    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 4;

    public Takeoff() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
        this.cardsToPreview = new Impact();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        choices.add(new GuardFormation());
        choices.add(new AttackFormation());

        if (this.upgraded) {
            for (AbstractCard c : choices) {
                c.upgrade();
            }
        }

        addToBot(new ChooseOneAction(choices));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Takeoff();
    }
}
