package org.wikapidia.sr.ensemble;

import com.typesafe.config.Config;
import gnu.trove.map.TIntDoubleMap;
import gnu.trove.map.hash.TIntDoubleHashMap;
import gnu.trove.set.TIntSet;
import org.wikapidia.conf.Configuration;
import org.wikapidia.conf.ConfigurationException;
import org.wikapidia.conf.Configurator;
import org.wikapidia.core.WikapidiaException;
import org.wikapidia.core.dao.DaoException;
import org.wikapidia.core.dao.LocalPageDao;
import org.wikapidia.core.lang.Language;
import org.wikapidia.sr.BaseMonolingualSRMetric;
import org.wikapidia.sr.MonolingualSRMetric;
import org.wikapidia.sr.SRResult;
import org.wikapidia.sr.SRResultList;
import org.wikapidia.sr.dataset.Dataset;
import org.wikapidia.sr.disambig.Disambiguator;
import org.wikapidia.sr.utils.KnownSim;
import org.wikapidia.sr.utils.SimUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *@author Matt Lesicko
 **/
public class MostSimilarCosineMetric extends BaseMonolingualSRMetric {
    final double TRAINING_SCORE_CUTOFF = 0.7;
    final int MAX_RESULTS = 500;
    MonolingualSRMetric baseMetric;

    public MostSimilarCosineMetric(Language language, Disambiguator disambiguator, LocalPageDao pageHelper, MonolingualSRMetric baseMetric){
        super(language, pageHelper, disambiguator);
        this.baseMetric=baseMetric;
    }

    @Override
    public String getName() {
        return "MostSimilarCosine";
    }

    @Override
    public SRResult similarity(int page1, int page2, boolean explanations) throws DaoException {
        SRResultList mostSimilar1 = baseMetric.mostSimilar(page1,MAX_RESULTS);
        SRResultList mostSimilar2 = baseMetric.mostSimilar(page2,MAX_RESULTS);
        return new SRResult(SimUtils.cosineSimilarity(mostSimilar1.asTroveMap(), mostSimilar2.asTroveMap()));
    }

    @Override
    public void trainSimilarity(Dataset dataset) throws DaoException {
        List<KnownSim> highScores = new ArrayList<KnownSim>();
        for (KnownSim ks : dataset.getData()){
            if (ks.similarity > TRAINING_SCORE_CUTOFF){
                highScores.add(ks);
            }
        }
        Dataset highDataset = new Dataset(dataset + "-mostSimilar", dataset.getLanguage(),highScores);
        baseMetric.trainMostSimilar(highDataset,MAX_RESULTS,null);
    }

    @Override
    public SRResultList mostSimilar(int page, int maxResults) throws DaoException {
        return mostSimilar(page, maxResults,null);
    }

    @Override
    public SRResultList mostSimilar(int page, int maxResults, TIntSet validIds) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public TIntDoubleMap getVector(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeCosimilarity(String path, int maxHits, TIntSet rowIds, TIntSet colIds) throws IOException, DaoException, WikapidiaException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void readCosimilarity(String path) throws IOException {
        throw new UnsupportedOperationException();
    }

    public static class Provider extends org.wikapidia.conf.Provider<MonolingualSRMetric> {
        public Provider(Configurator configurator, Configuration config) throws ConfigurationException {
            super(configurator, config);
        }

        @Override
        public Class getType() {
            return MonolingualSRMetric.class;
        }

        @Override
        public String getPath() {
            return "sr.metric.local";
        }

        @Override
        public MonolingualSRMetric get(String name, Config config, Map<String, String> runtimeParams) throws ConfigurationException {
            if (!config.getString("type").equals("mostsimilarcosine")) {
                return null;
            }

            if (!runtimeParams.containsKey("language")) {
                throw new IllegalArgumentException("Monolingual SR Metric requires 'language' runtime parameter");
            }
            Language language = Language.getByLangCode(runtimeParams.get("language"));
            if (!config.hasPath("metrics")){
                throw new ConfigurationException("Ensemble metric has no base metrics to use.");
            }
            MostSimilarCosineMetric sr = new MostSimilarCosineMetric(
                    language,
                    getConfigurator().get(Disambiguator.class,config.getString("disambiguator")),
                    getConfigurator().get(LocalPageDao.class,config.getString("pageDao")),
                    getConfigurator().get(MonolingualSRMetric.class,config.getString("basemetric"))
            );

            return sr;
        }

    }
}
