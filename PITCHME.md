#HSLIDE
<!-- .slide: data-autoslide="10000" -->

![LOGO](https://d1z75bzl1vljy2.cloudfront.net/img/gp-logo.png)

#HSLIDE

- GitHub Flavored Markdown +
- Code Block and GIST Slides
- Image and Video Slides
- Custom Logos and Backgrounds
- Multiple Themes And More
- <span style="color: #e49436">Plus...</span>
- Your Slideshow Is Part Of Your Project
- Under Git Version Control Within Your Git Repo

#HSLIDE

 Creando un ImageLoader 

¿Qué librerias usamos normalmente para cargar imagenes ?. Por ejemplo,si queremos mostrar una imagen en un vista o lista 

- Picasso by Square [http://square.github.io/picasso/](http://square.github.io/picasso/)

- Glide [https://github.com/bumptech/glide](https://github.com/bumptech/glide)

- Fresco by Facebook [https://github.com/facebook/fresco](https://github.com/facebook/fresco)

#HSLIDE
 Creando un ImageLoader 

Picaso y Glide son muy similares , Glide y Fresco cuentan algunas  funcionalidades adicionales.

```
   Picasso.with(imageView.getContext())
                .load(url)
                .into(imageView);
```

```
   Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
```

```
   <com.facebook.drawee.view.SimpleDraweeView
       android:id="@+id/sdvImage"
       android:layout_width="130dp"
       android:layout_height="130dp"
       fresco:placeholderImage="@drawable/myPlaceholderImage" />
```

```
   Fresco.initialize(context);
   Uri imageUri = Uri.parse("https://i.imgur.com/tGbaZCY.jpg");
   SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.sdvImage);
   draweeView.setImageURI(imageUri);
```

#HSLIDE
 Creando un ImageLoader 

La idea es no depender de una en particular , no tener código suelto en nuestro código y en su momento poder escoger trabajar con una u otra.

Definimos el comportamiento del ImageLoader

```
      public interface ImageLoader {

          void load(String url, ImageView imageView);
          void loadCircle(String url, ImageView imageView);
          void loadLocal(String path, ImageView imageView);
      }
```
#HSLIDE
 Creando un ImageLoader 
 ImageLoaderHelper seria la implementación:
 
```
public class ImageLoaderHelper {

    public static final int GLIDE=1;
    public static final int PICASSO=2;

    private int type=GLIDE;
    private ImageLoader imageLoader;

    public ImageLoaderHelper(int type) {
        this.type = type;
        imageLoader= factory();
    }

    public ImageLoader getLoader() {
        return imageLoader;
    }

    private ImageLoader factory()
    {
        switch (type)
        {
            case PICASSO:
               return new PicassoLoader();
            case GLIDE:
                return new GlideLoader();
            default:
                return new GlideLoader();
        }
    }
}
```

#HSLIDE
<!-- .slide: data-autoslide="8000" -->

### Go for it.
### Just add <span style="color: #e49436; text-transform: none">PITCHME.md</span> ;)
