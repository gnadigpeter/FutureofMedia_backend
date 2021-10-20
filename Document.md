Működés


Company listázás:
a) URL: /company/all?page=0&size=10
b) URL: /company/all

a) esetben megadhatjuk hogy hanyadik oldalt, és oldalanként mennyi Companyt szereténjk átadni,
b) esetben az összeset átadja
Json formátumban
{
    "id": 10,
    "name": "Company #3",
    "createDate": "2021-10-20T09:00:06.679+00:00",
    "modifyDate": "2021-10-20T09:00:06.679+00:00"
}


Company hozzáadása:
URL: /company/add
Json formátumban a company nevét kell megadni
{
    "name": "example comany"
}

Company szerkesztés (update)
URL: /company/update/companyId/{companyId}
Json formátumban a company nevét kell megadni
{
    "name": "Company #XX"
}

Company törlése
URL: /company/delete/companyId/{companyId}
Az URL ben lévő comapnyt törli

--------------------------------------------------------------------------------------------------------------------------------------------------

Contact:
Contact listázása:
a) URL: /contact/all?page=0&size=10
b) URL: /contact/all

a) esetben megadhatjuk hogy hanyadik oldalt, és oldalanként mennyi Companyt szereténjk átadni,
b) esetben az összeset átadja
Json formátumban
{
    "id": 58,
    "lastName": "last",
    "firstName": "first",
    "email": "bela@gmail.com",
    "phoneNumber": "+36701112222",
    "company": {
        "id": 8,
        "name": "Company #20",
        "createDate": "2021-10-20T08:59:39.102+00:00",
        "modifyDate": "2021-10-20T20:24:26.310+00:00"
        },
    "note": "note",
    "status": "Active",
    "createDate": "2021-10-20T20:05:42.740+00:00",
    "modifyDate": "2021-10-20T20:05:42.740+00:00"
}

Contactok cégenként listázás:
URL: /contact/all/companyId/{companyId}"
Az Urlben megadott céghez tartozó kapcsolat tartókat adja vissza Json formátumban

Contact hozzáadás:
URL: /contact/add
Json formátumba adjuk meg az adatokat:
{
    "lastName": "last",
    "firstName": "first",
    "email": "bela@gmail.com",
    "phoneNumber": "06701112222",
    "company": {
            "id": 8        
             },
    "note": "note",
    "status": "Active"
}

Contact szerkesztése:
URL: /contact/update/{contactId}
Json formátumba adjuk meg a szükséges adatokat
{
    "lastName": "last",
    "firstName": "first",
    "email": "bela@gmail.com",
    "phoneNumber": "06701112222",
    "company": {
            "id": 8        
             },
    "note": "note",
    "status": "Active"
}

Contact törlés:
URL: /contact/delete/{contactId}
Az URL ben lévő contactot törli

