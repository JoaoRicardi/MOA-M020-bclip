package br.com.digitalhouse.bclip.service.api;

import br.com.digitalhouse.bclip.model.NoticiaFromApiResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NoticiasApiPreferencias {

    @GET("top-headlines")
    Observable<NoticiaFromApiResponse> getNoticiasFromApiPreferencias(@Query("apiKey") String apiKey,
                                                          @Query("format") String format,
                                                          @Query("category")String category,
                                                          @Query("q") String search,
                                                          @Query("pageSize") int pageSize,
                                                          @Query("country") String country);
}
