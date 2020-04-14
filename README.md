# Issue 186 

Create a Product entity:

`curl -X POST http://localhost:8080/products -H 'Content-Type: application/hal+json' -d {}`

Result:
```
{
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/products/1"
    },
    "product" : {
      "href" : "http://localhost:8080/products/1"
    },
    "images" : {
      "href" : "http://localhost:8080/products/1/images"
    }
  }
}
```

Create an image entity:

`curl -X POST http://localhost:8080/images -H 'Content-Type: application/hal+json' -d '{}'`

Result:
```
{
  "contentId" : null,
  "contentLength" : null,
  "mimeType" : null,
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/images/2"
    },
    "image" : [ {
      "href" : "http://localhost:8080/images/2"
    }, {
      "href" : "http://localhost:8080/content/products/2"
    } ],
    "products" : {
      "href" : "http://localhost:8080/content/products/2"
    },
    "product" : {
      "href" : "http://localhost:8080/images/2/product"
    }
  }
}
```

Associate content with that entity:

`curl http://localhost:8080/content/products/2 -Ffile=@/tmp/test1.txt`

Create another Image entity:

`curl -X POST http://localhost:8080/images -H 'Content-Type: application/hal+json' -d '{}'`

Result:
```
{
  "contentId" : null,
  "contentLength" : null,
  "mimeType" : null,
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/images/3"
    },
    "image" : [ {
      "href" : "http://localhost:8080/images/3"
    }, {
      "href" : "http://localhost:8080/content/products/3"
    } ],
    "products" : {
      "href" : "http://localhost:8080/content/products/3"
    },
    "product" : {
      "href" : "http://localhost:8080/images/3/product"
    }
  }
}
```

Associate content with that entity:

`curl http://localhost:8080/content/products/3 -Ffile=@/tmp/test2.txt`

Add the images to the product:

`curl -X PUT http://localhost:8080/products/1/images -H "Content-Type:text/uri-list" -d "http://localhost:8080/images/2
http://localhost:8080/images/3"`

Now you can follow the hyperlinks:

`curl http://localhost:8080/products/1`

Result:
```
{
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/products/1"
    },
    "product" : {
      "href" : "http://localhost:8080/products/1"
    },
    "images" : {
      "href" : "http://localhost:8080/products/1/images"
    }
  }
}
```

Follow "images":

`http://localhost:8080/products/1/images`

Result:
```
{
  "_embedded" : {
    "images" : [ {
      "contentId" : null,
      "contentLength" : null,
      "mimeType" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/images/2"
        },
        "image" : [ {
          "href" : "http://localhost:8080/images/2"
        }, {
          "href" : "http://localhost:8080/content/products/2"
        } ],
        "products" : {
          "href" : "http://localhost:8080/content/products/2"
        }
      }
    }, {
      "contentId" : null,
      "contentLength" : null,
      "mimeType" : null,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/images/3"
        },
        "image" : [ {
          "href" : "http://localhost:8080/images/3"
        }, {
          "href" : "http://localhost:8080/content/products/3"
        } ],
        "products" : {
          "href" : "http://localhost:8080/content/products/3"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/products/1/images"
    }
  }
}
```

And now follow the content links, either:

`http://localhost:8080/content/products/2`

Result:
```
hello spring content world one
```

or

`http://localhost:8080/content/products/3`

Result:
```
hello spring content world two
```

