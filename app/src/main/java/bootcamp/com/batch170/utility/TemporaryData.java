package bootcamp.com.batch170.utility;

import bootcamp.com.batch170.models.ModelPhotos;

/**
 * Created by Eric on 10/10/2018.
 */

public class TemporaryData {
    private static ModelPhotos modelPhotos;

    public static ModelPhotos getModelPhotos() {
        return modelPhotos;
    }

    public static void setModelPhotos(ModelPhotos modelPhotos) {
        TemporaryData.modelPhotos = modelPhotos;
    }
}
