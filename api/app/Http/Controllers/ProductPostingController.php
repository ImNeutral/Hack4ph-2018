<?php

namespace App\Http\Controllers;

use App\ProductPosting;
use App\SystemCode;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Input;

class ProductPostingController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $user_type = session('user_type');
        $post_type = '';
        if($user_type == 'SUPPLIER') {
            $post_type = 'BUY';
        } else if ($user_type == 'BUYER') {
            $post_type = 'SELL';
        } else {
            $post_type = 'SELL';
        }

        if ( isset( $_GET['page'] ) && isset( $_GET['per-row'] ) ) {
            $page   = Input::get('page');
            $perRow = Input::get('per-row');
            $offset = ($page- 1) * $perRow;
            $sc = SystemCode::where(['category' => 'POST_TYPE'])->where(['value' => $post_type])->first();
            $productPostings = $sc->ProductPostings()->offset($offset)->limit($perRow)->get();

            return response()->json( $productPostings, 200);
        } else if( isset( $_GET['count-total'] ) ) {
            $sc = SystemCode::where(['category' => 'POST_TYPE'])->where(['value' => $post_type])->first();
            $countTotal = $sc->ProductPostings()->count();
            $response = [
                'countTotal' => $countTotal
            ];
            return response()->json( $response, 200);
        } else {
            return response()->json(['error' => 'No product posting found'], 401);
        }
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $productPosting = ProductPosting::find($id);

        if( $productPosting ) {
            $response = [
                "productPosting" => $productPosting
            ];
            return response()->json($response, 200);
        } else {
            return response()->json(['error' => 'Product Posting not found'], 401);
        }
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }

}
