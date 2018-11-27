<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

/* ALL Photos are gonna be created later */

//Login ---------------------------------------------------------------------
/**
 * to check if user and password is correct, access: /api/login?email=myemail&password=mypassword
 * when you use /api/login, means you are logging in. once logged in, you will get access to the API
 */

Route::get('/login', "LoginController@show");
Route::get('/logout', "LoginController@logout");




//User details -> including business details and account details --------------
/**
 * get user data, access: /api/user/id
 * example: /api/user/1
 */
Route::get('/user/{id}', "UserController@show")->middleware('authenticated');
/**
 * For post, supply the following values to add user
 * first_name, middle_name, last_name, email, password, birth_date, contact_number, user_type
 * business_name, business_permit, business_permit_photo
 */
Route::post('/user', "UserController@store")->middleware('authenticated');
/**
 * For PUT, same parameters with POST, exept, it has _method=POST  (this is a value just like first_name, last_name, etc.)
 */
Route::put('/user/{id}', 'UserController@update')->middleware('authenticated');
Route::delete('/user/{id}', 'UserController@destroy')->middleware('authenticated');



// Products ---------------------------------------------------------------------
/**
 * to get product posting details. access: /api/product-posting/id
 * ex: /api/user/1
 */
Route::get('/product-posting/{id}', "ProductPostingController@show")->middleware('authenticated');
Route::post('/product-posting', "ProductPostingController@store")->middleware('authenticated');
/**
 * to get product postings by page, access: /api/product-posting?page=pageNum&perRow=perRowNum
 * Ex: /api/product-posting?page=1&per-row=10
 * to get the total number of products available, access: /api/product-posting?count-total=true
 */
Route::get('/product-posting', "ProductPostingController@index")->middleware('authenticated');




