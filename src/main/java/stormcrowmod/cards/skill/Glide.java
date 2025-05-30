package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import stormcrowmod.actions.CreateThrusterAction;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class Glide extends BaseCard {
    public static final String ID = makeID(Glide.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 1;

    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 3;

    public Glide() {
        super(ID, info);
        setMagic(MAGIC);
        setBlock(BLOCK, UPG_BLOCK);

        tags.add(PilotTags.PULSE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        addToBot(new PulseAction(new ApplyPowerAction(p, p, new ArtifactPower(p, this.magicNumber))));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Glide();
    }
}
