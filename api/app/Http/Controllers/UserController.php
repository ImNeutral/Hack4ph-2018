<?php

namespace App\Http\Controllers;

use App\SystemCode;
use App\User;
use App\Barangay;
use App\UserBusiness;
use Illuminate\Contracts\Session\Session;
use Illuminate\Http\Request;
use PharIo\Manifest\Exception;

class UserController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
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
        try {
            $userType = SystemCode::where(['value' => $request->input('user_type')])->first();
            $barangay = Barangay::where(['barangay_name' => $request->input('barangay')])->first();

            $user = new User();
            $user->first_name = $request->input('first_name');
            $user->middle_name = $request->input('middle_name');
            $user->last_name = $request->input('last_name');
            $user->email = $request->input('email');
            $user->password = bcrypt($request->input('password'));
            $user->birth_date = $request->input('birth_date');
            $user->contact_number = $request->input('contact_number');
            $user->user_photo = "support later";

            if ($userType) {
                $user->user_type = $userType->id;
            }
            if ($barangay) {
                $user->barangay = $barangay->id;
            }
            $user->save();

            $this->storeUserBusiness($user, $request);

            return response()->json(['success' => 'Successfully Added User and User Business'], 200);
        } catch (Exception $e) {
            return response()->json(['error' => 'Bad Request'], 400);
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    { 
        $user = User::find($id);

        if( $user ) {
            $response = [
                "user" => $user,
                "user_business" => $user->UserBusiness()->first()
            ];
            return response()->json($response, 200);
        } else {
            return response()->json(['error' => 'user not found'], 401);
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
        $user = User::find($id);
        if( $user ) {
            try {
                $user->first_name       = $request->input('first_name');
                $user->middle_name      = $request->input('middle_name');
                $user->last_name        = $request->input('last_name');
                $user->email            = $request->input('email');
                $user->password         = bcrypt($request->input('password'));
                $user->birth_date       = $request->input('birth_date');
                $user->contact_number   = $request->input('contact_number');
                $user->user_photo       = "support later";
                $this->updateUserBusiness($user, $request);
                $user->save();
                return response()->json(['success' => 'Successfully Updated User.'], 200);
            } catch (Exception $e) {
                return response()->json(['error' => 'Something went wrong.'], 401);
            }
        } else {
            return response()->json(['error' => 'No user found, update failed.'], 401);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $res = User::where('id',$id)->delete();
        if( $res ) {
            return response()->json(['success' => 'Successfully Deleted a User.'], 200);
        } else {
            return response()->json(['error' => 'No User FOund, delete failed.'], 401);
        }
    }

    public function storeUserBusiness(User $user, Request $request) {
        $userBusiness = new UserBusiness();
        $userBusiness->business_name        = $request->input('business_name');
        $userBusiness->business_permit      = $request->input('business_permit');
        $userBusiness->business_permit_photo       = "set later";
        $user->userBusiness()->save($userBusiness);
    }

    public function updateUserBusiness(User $user, Request $request) {
        $userBusiness                           =  $user->UserBusiness()->first();
        $userBusiness->business_name            = $request->input('business_name');
        $userBusiness->business_permit          = $request->input('business_permit');
        $userBusiness->business_permit_photo    = "set later";
        $userBusiness->save();
    }
}
