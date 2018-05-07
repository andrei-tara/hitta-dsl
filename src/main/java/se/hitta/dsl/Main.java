package se.hitta.dsl;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String script =
                        "fetch h https://api.hitta.se/search/v7/app/combined/within/57.840703831916%3A11.728156448084002%2C57.66073920808401%3A11.908121071915998/?range.to=101&range.from=1&geo.hint=57.75072152%3A11.81813876&sort.order=relevance&query=lunch\n" +
                        "foreach c h.result.companies.company\n" +
                        " fetch v https://api.foursquare.com/v2/venues/search?ll={c.address[0].coordinate.north},{c.address[0].coordinate.east}&client_id=QLBFCMENME3VYKKZYN5Y1A1UFSY0JAC5C4K3S5MHWQ34X1Z3&client_secret=NGBCX3NVHXJYAZ4WCVRZQVKV3PYBYFI0JWVII2JPQ5KRN41T&intent=match&name={c.displayName}&v=20180401\n" +
                        " fetch d https://api.foursquare.com/v2/venues/{v.response.venues[0].id}/photos?client_id=QLBFCMENME3VYKKZYN5Y1A1UFSY0JAC5C4K3S5MHWQ34X1Z3&client_secret=NGBCX3NVHXJYAZ4WCVRZQVKV3PYBYFI0JWVII2JPQ5KRN41T&v=20180401\n" +
                        " foreach i d.response.photos.items\n" +
                        "  download {i.prefix}original{i.suffix} ./localpath/photos/{c.id}_{i.suffix}";


        ScriptRunner.run(script);

    }
}
