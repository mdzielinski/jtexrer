package mdse.jtexrer.fetching.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("dummy")
@AllArgsConstructor
public class DummyClient implements RestClient {

    @Override
    public ResponseEntity<JsonNode> sendGet(String url, String apiKeyName, String apiKeyValue) {

        log.info("DummyClient generates dummy exchange rates!");
        return new ResponseEntity<>(generate(), HttpStatus.OK);
    }

    private static JsonNode generate() {
        JsonNode responseBody;
        try {
            responseBody = new ObjectMapper().readTree(echangeRatesJson());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }

    private static String echangeRatesJson() {
        return """
                {
                                   "success": true,
                                   "timestamp": 1671231662,
                                   "base": "EUR",
                                   "date": "2022-12-16",
                                   "rates": {
                                       "AED": 3.892375,
                                       "AFN": 92.195549,
                                       "ALL": 114.714519,
                                       "AMD": 419.065255,
                                       "ANG": 1.910452,
                                       "AOA": 534.490733,
                                       "ARS": 182.739784,
                                       "AUD": 1.57813,
                                       "AWG": 1.907486,
                                       "AZN": 1.805722,
                                       "BAM": 1.949574,
                                       "BBD": 2.140511,
                                       "BDT": 110.567746,
                                       "BGN": 1.955074,
                                       "BHD": 0.399175,
                                       "BIF": 2186.190945,
                                       "BMD": 1.059714,
                                       "BND": 1.438323,
                                       "BOB": 7.325424,
                                       "BRL": 5.631645,
                                       "BSD": 1.060088,
                                       "BTC": 6.3349663e-05,
                                       "BTN": 87.774854,
                                       "BWP": 13.76779,
                                       "BYN": 2.675913,
                                       "BYR": 20770.403551,
                                       "BZD": 2.136923,
                                       "CAD": 1.455995,
                                       "CDF": 2154.399895,
                                       "CHF": 0.98734,
                                       "CLF": 0.034046,
                                       "CLP": 939.441021,
                                       "CNY": 7.389711,
                                       "COP": 5080.408917,
                                       "CRC": 630.371244,
                                       "CUC": 1.059714,
                                       "CUP": 28.082433,
                                       "CVE": 110.744291,
                                       "CZK": 24.253728,
                                       "DJF": 188.332872,
                                       "DKK": 7.445876,
                                       "DOP": 58.606328,
                                       "DZD": 145.588811,
                                       "EGP": 26.121194,
                                       "ERN": 15.895717,
                                       "ETB": 56.748125,
                                       "EUR": 1,
                                       "FJD": 2.356858,
                                       "FKP": 0.86822,
                                       "GBP": 0.872624,
                                       "GEL": 2.808657,
                                       "GGP": 0.86822,
                                       "GHS": 9.484858,
                                       "GIP": 0.86822,
                                       "GMD": 65.702674,
                                       "GNF": 9314.890541,
                                       "GTQ": 8.343149,
                                       "GYD": 221.790966,
                                       "HKD": 8.249295,
                                       "HNL": 26.228345,
                                       "HRK": 7.548139,
                                       "HTG": 154.247577,
                                       "HUF": 405.955829,
                                       "IDR": 16548.554101,
                                       "ILS": 3.670537,
                                       "IMP": 0.86822,
                                       "INR": 87.619634,
                                       "IQD": 1548.242836,
                                       "IRR": 43872.179303,
                                       "ISK": 151.437241,
                                       "JEP": 0.86822,
                                       "JMD": 162.721396,
                                       "JOD": 0.75166,
                                       "JPY": 144.868308,
                                       "KES": 130.398273,
                                       "KGS": 90.023151,
                                       "KHR": 4355.426831,
                                       "KMF": 492.02945,
                                       "KPW": 953.731633,
                                       "KRW": 1388.183969,
                                       "KWD": 0.325163,
                                       "KYD": 0.883419,
                                       "KZT": 494.915512,
                                       "LAK": 18375.449226,
                                       "LBP": 1612.885788,
                                       "LKR": 389.577061,
                                       "LRD": 163.196397,
                                       "LSL": 18.736156,
                                       "LTL": 3.129062,
                                       "LVL": 0.641011,
                                       "LYD": 5.102567,
                                       "MAD": 11.132835,
                                       "MDL": 20.52404,
                                       "MGA": 4689.236884,
                                       "MKD": 61.460407,
                                       "MMK": 2226.154123,
                                       "MNT": 3636.390078,
                                       "MOP": 8.497093,
                                       "MRO": 378.317882,
                                       "MUR": 46.850534,
                                       "MVR": 16.323567,
                                       "MWK": 1082.50229,
                                       "MXN": 20.968044,
                                       "MYR": 4.688711,
                                       "MZN": 67.641974,
                                       "NAD": 18.736152,
                                       "NGN": 472.071403,
                                       "NIO": 38.577554,
                                       "NOK": 10.489095,
                                       "NPR": 140.441048,
                                       "NZD": 1.663864,
                                       "OMR": 0.407659,
                                       "PAB": 1.060088,
                                       "PEN": 4.059104,
                                       "PGK": 3.735534,
                                       "PHP": 58.69282,
                                       "PKR": 238.489138,
                                       "PLN": 4.690158,
                                       "PYG": 7648.360111,
                                       "QAR": 3.858156,
                                       "RON": 4.919729,
                                       "RSD": 117.359438,
                                       "RUB": 68.749017,
                                       "RWF": 1128.595907,
                                       "SAR": 3.985659,
                                       "SBD": 8.693456,
                                       "SCR": 13.997381,
                                       "SDG": 602.977891,
                                       "SEK": 11.033327,
                                       "SGD": 1.440262,
                                       "SHP": 1.459655,
                                       "SLE": 19.800742,
                                       "SLL": 19811.362317,
                                       "SOS": 602.97789,
                                       "SRD": 33.51563,
                                       "STD": 21933.94991,
                                       "SVC": 9.275928,
                                       "SYP": 2662.558562,
                                       "SZL": 18.736143,
                                       "THB": 38.150078,
                                       "TJS": 10.807662,
                                       "TMT": 3.719598,
                                       "TND": 3.344724,
                                       "TOP": 2.489217,
                                       "TRY": 19.752446,
                                       "TTD": 7.196041,
                                       "TWD": 32.623844,
                                       "TZS": 2471.254492,
                                       "UAH": 38.961277,
                                       "UGX": 3879.921518,
                                       "USD": 1.059714,
                                       "UYU": 40.966031,
                                       "UZS": 11948.280968,
                                       "VEF": 1611059.062358,
                                       "VES": 16.480945,
                                       "VND": 25014.559991,
                                       "VUV": 123.932256,
                                       "WST": 2.84676,
                                       "XAF": 653.831147,
                                       "XAG": 0.045649,
                                       "XAU": 0.000591,
                                       "XCD": 2.863932,
                                       "XDR": 0.79661,
                                       "XOF": 653.317781,
                                       "XPF": 119.61531,
                                       "YER": 265.250339,
                                       "ZAR": 18.690454,
                                       "ZMK": 9538.705662,
                                       "ZMW": 18.753004,
                                       "ZWL": 341.227626
                                   }
                               }
                """;
    }

}
