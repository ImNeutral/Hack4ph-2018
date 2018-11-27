<?php

namespace App;

use Illuminate\Notifications\Notifiable;
use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Foundation\Auth\User as Authenticatable;

class User extends Authenticatable
{
    protected $table = "users";
    public $timestamps = false;

    public function UserBusiness() {
        return $this->hasOne('App\UserBusiness');
    }

    public function UserType() {
        return $this->belongsTo('App\SystemCode', 'id');
    }

}

