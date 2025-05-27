package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ChargeThrustersAction;
import stormcrowmod.actions.TwinThrustersAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class ChargeThrusters extends BaseCard {
    public static final String ID = makeID(ChargeThrusters.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            -1 //Can use -1 for X, or -2 for unplayable
    );

    public ChargeThrusters() {
        super(ID, info);

        this.cardsToPreview = new Thruster();
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ORB_SLOT_GAIN"));
        addToBot(new ChargeThrustersAction(p, this.freeToPlayOnce, this.energyOnUse, this.upgraded));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ChargeThrusters();
    }
}
