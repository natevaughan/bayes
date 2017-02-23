package com.natevaughan.bayes.predictor;

import com.natevaughan.bayes.variable.BinaryTarget;
import com.natevaughan.bayes.variable.BooleanValue;
import com.natevaughan.bayes.variable.CategoricalVariable;
import com.natevaughan.bayes.variable.PredictionValue;
import com.natevaughan.bayes.variable.Target;
import com.natevaughan.bayes.variable.Value;
import com.natevaughan.bayes.variable.LinesToValuesProcessor;
import groovy.lang.Tuple2;
import groovy.transform.TypeChecked;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by nate on 2/13/17.
 */
public class SparseDatasetTest {

    private CategoricalVariable targetVar = new CategoricalVariable("TARGET");
    private Value targetVal = new BooleanValue(true, targetVar);
    private Value targetAntiVal = new BooleanValue(false, targetVar);

    List<Tuple2<Value, String>> trainingData = Arrays.asList(new Tuple2[] {
            new Tuple2(targetVal, "Rocketmiles is fact not a fake like the others , when i compare your price with otheres 20 percents differ , and also with nice hotels"),
            new Tuple2(targetVal, "For my first experience, I think it is a good oppurtunity for those who travels more like me to earn extra miles and find good hotels. Thank you for concern."),
            new Tuple2(targetAntiVal, "Sometimes the hotel does not give you the best rooms if you book through a third party"),
            new Tuple2(targetAntiVal, "Basically, stop lying.  You sent me an email that you accredited me 9,000 miles for a purchase and you never did.  You are no more than a bunch of liars and I will never recommend you nor use you again. And be thankful I don't denounce you for fraud."),
            new Tuple2(targetVal, "Overall things were very good.  A bit of confusion with room types when looking on the Rocketmiles website (I believe the two queen beds room was listed as two twin's).  It might be worth a quick review to align room types on Rockermiles website with room types on the hotels websites.  Other than that, things were good.  Thank you!  We will definitively use rocketmiles again."),
            new Tuple2(targetVal, "Easy booking. Real miles. They accumulate quickly. Best program ever. Thanks."),
            new Tuple2(targetAntiVal, "Room small. Hilton gives me my perks for being a high level member- this hotel did not!!"),
            new Tuple2(targetAntiVal, "This is the 3rd time I have booked with Rocket Miles to show and find out the place I'm staying at has some type of renovation going on.  Not good.")
    });

    // XXX todo port to Spock
    @Test
    public void simpleSparsePredictorTest() {
        NaiveSparsePredictor predictor = createSparsePredictor();
        predictor.trainAll(createTrainingRows());
        System.out.println(predictor.predict(LinesToValuesProcessor.convert("small compare fake email third")));
        System.out.println(predictor.predict(LinesToValuesProcessor.convert("lying fake miles never")));
        System.out.println(predictor.predict(LinesToValuesProcessor.convert("good Rocketmiles did not")));
        System.out.println("------------------------");
        for (Tuple2<Value, String> tuple2 : trainingData) {
            System.out.println("Predicted " + tuple2.getFirst().getName() + ", was " + predictor.predict(LinesToValuesProcessor.convert(tuple2.getSecond())));
        }
//        assertNotNull(prediction);
//        assertTrue(prediction.getVariable().equals(targetVar));
//        assertTrue(prediction.getName().equals(false));
    }

    private Collection<Tuple2<Value, Collection<Value>>> createTrainingRows() {
        Collection<Tuple2<Value, Collection<Value>>> rows = new ArrayList<>();
        for (int i = 0; i < trainingData.size(); ++i) {
            rows.add(new Tuple2<Value, Collection<Value>>(trainingData.get(i).getFirst(), LinesToValuesProcessor.stripNonAlphaAndConvert(trainingData.get(i).getSecond())));
        }
        return rows;
    }

    private NaiveSparsePredictor createSparsePredictor() {
        Target t = new BinaryTarget(targetVar, targetVal);
        t.setEpsilon(5.0D);
        NaiveSparsePredictor predictor = new NaiveSparsePredictor(t);
        return predictor;
    }
}
